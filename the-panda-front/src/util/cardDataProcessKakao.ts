import { KakaoProductItem } from "types/productTypes/productType,";

export const cardDataProcessKakao = (products: KakaoProductItem[], keyword: string) => { 

  //데이터를 가공하여 
  let totalProductsCount = products.length;

  let freeDeliveryCount = products.filter(product => /true/.test(product.freeDelivery)).length;
  let totalNewProductsCount = products.filter(product => /true/.test(product.newProduct)).length;

  let minSalesCount = Infinity;
  let maxSalesCount = 0;
  let totalSalesCount = 0;

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
    if (product.totalSaleCount !== "") {
      sellingCount = parseInt(product.totalSaleCount);
      totalSalesCount += sellingCount;
      minSalesCount = Math.min(minSalesCount, sellingCount);
      maxSalesCount = Math.max(maxSalesCount, sellingCount);
       }


    
    // Prices
    const salesPrice = parseInt(product.discountedPrice.replace(/\D/g, ""));
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
  // TOP 5 매출을 구합니다.
  const top5Sales = getTop5Sales(products);
  const top1 = top5Sales[0] || {};
  const top2 = top5Sales[1] || {};
  const top3 = top5Sales[2] || {};
  const top4 = top5Sales[3] || {};
  const top5 = top5Sales[4] || {};

  return {
    keyword: keyword, 
    totalProductsCount,
    freeDeliveryCount,
    totalNewProductsCount,
    minSalesCount,
    aveSalesCount,
    maxSalesCount,
    maxPrice,
    minPrice,
    avePrice,
    review0,
    review1to49,
    review50to100,
    review100plus,
    top1Sales: top1.productSale || 0,
    top1StoreName: top1.storeName || '',
    top1storeLinkPath : top1.storeLinkPath || '',
    top2Sales: top2.productSale || 0,
    top2StoreName: top2.storeName || '',
    top2storeLinkPath : top2.storeLinkPath || '',
    top3Sales: top3.productSale || 0,
    top3StoreName: top3.storeName || '',
    top3storeLinkPath : top3.storeLinkPath || '',
    top4Sales: top4.productSale || 0,
    top4StoreName: top4.storeName || '',
    top4storeLinkPath : top4.storeLinkPath || '',
    top5Sales: top5.productSale || 0,
    top5StoreName: top5.storeName || '',
    top5storeLinkPath : top5.storeLinkPath || '',
  };
}

const getTop5Sales = (products: KakaoProductItem[]) => {
  // 상품들을 매출 순으로 정렬합니다.
  const sortedProducts = products.sort((a, b) => {
    const salesA = parseInt(a.totalSaleCount) * parseInt(a.discountedPrice.replace(/\D/g, ""));
    const salesB = parseInt(b.totalSaleCount) * parseInt(b.discountedPrice.replace(/\D/g, ""));
    return salesB - salesA;
  });

  // 상위 5개 상품의 매출을 추출합니다.
  const top5Sales = sortedProducts.slice(0, 5).map(product => {
    const productSale = parseInt(product.totalSaleCount) * parseInt(product.discountedPrice.replace(/\D/g, ""));
    return {
      storeName: product.storeName,
      productSale: productSale,
      storeLinkPath: product.storeLinkPath
    };
  });

  return top5Sales;
};