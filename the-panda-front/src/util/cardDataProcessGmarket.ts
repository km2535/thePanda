import { GmarketProductItem } from "types/productTypes/productType,";

export const cardDataProcessGmarket = (products: GmarketProductItem[], keyword: string) => { 

  //데이터를 가공하여 
  let totalProductsCount = products.length;
  let todayDeliveryCount = products.filter(product => /오늘출발/.test(product.DeliveryInfo)).length;

  let freeDeliveryCount = products.filter(product => /무료배송/.test(product.freeDelivery)).length;

  let minSalesCount = Infinity;
  let maxSalesCount = 0;
  let totalSalesCount = 0;

  let satisfy50minus = 0;
  let satisfy50to60 = 0;
  let satisfy60to70 = 0;
  let satisfy70to80 = 0;
  let satisfy80to90 = 0;
  let satisfy90to100 = 0;

  let maxPrice = 0;
  let minPrice = Infinity;
  let avePriceSum = 0;
  let review0 = 0;
  let review1to49 = 0;
  let review50to100 = 0;
  let review100plus = 0;

     products.forEach(product => {
    // Sales count
       let sellingCount = 0;
    if (product.sellingCount !== "") {
      sellingCount = parseInt(product.sellingCount.split(" ")[1].replace(",", ""));
      totalSalesCount += sellingCount;
      minSalesCount = Math.min(minSalesCount, sellingCount);
      maxSalesCount = Math.max(maxSalesCount, sellingCount);
       }
       if (product.sellingCount === "") {
         minSalesCount = 0;
       }


    // Satisfaction
    const satisfaction = parseInt(product.awardPoints.split(" ")[1]);
       if (satisfaction < 50) {
         satisfy50minus++;
      
    } else if (satisfaction >= 50 && satisfaction < 60) {
      satisfy50to60++;
    } else if (satisfaction >= 60 && satisfaction < 70) {
      satisfy60to70++;
    } else if (satisfaction >= 70 && satisfaction < 80) {
      satisfy70to80++;
    }else if (satisfaction >= 80 && satisfaction < 90) {
      satisfy80to90++;
    } else if (satisfaction >= 90 && satisfaction <= 100) {
      satisfy90to100++;
    }

    // Prices
    const salesPrice = parseInt(product.salesPrice.replace(/\D/g, ""));
    maxPrice = Math.max(maxPrice, salesPrice);
    minPrice = Math.min(minPrice, salesPrice);
    avePriceSum += salesPrice;

     // Reviews
    let reviewCount = 0;
    if (product.reviewCount !== "") {
      reviewCount = parseInt(product.reviewCount.replace(/\D/g, ""));
      if (reviewCount === 0) {
        review0++;
      } else if (reviewCount <= 49) {
        review1to49++;
      } else if (reviewCount <= 100) {
        review50to100++;
      } else {
        review100plus++;
      }
    }
  });
  
   const avePrice =Number((avePriceSum / totalProductsCount).toFixed(0));
     const aveSalesCount = Number((totalSalesCount / totalProductsCount).toFixed(0));

  return {
    keyword: keyword, 
    totalProductsCount,
    todayDeliveryCount,
    freeDeliveryCount,
    minSalesCount,
    aveSalesCount,
    maxSalesCount,
    satisfy50minus,
    satisfy50to60,
    satisfy60to70,
    satisfy70to80,
    satisfy80to90,
    satisfy90to100,
    maxPrice,
    minPrice,
    avePrice,
    review0,
    review1to49,
    review50to100,
    review100plus
  };
}