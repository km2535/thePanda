import { CoupangProductItem } from "types/productTypes/productType,";

export const cardDataProcessCoupang = (products:CoupangProductItem[], keyword:string) => {
  const countRocketName = (products: CoupangProductItem[], rocketName: string) => {
    return products.filter((product) => product.rocketName === rocketName).length;
  }
  const totalProductsCount = products.length;
  const countRegularProducts = countRocketName(products, "일반상품");
  const countRocketProducts = countRocketName(products, "로켓배송");
  const countSellerRocketProducts = countRocketName(products, "판매자로켓");
  const countRocketDirectProducts = countRocketName(products, "로켓직구");
  const countRocketInstallProducts = countRocketName(products, "로켓설치");
  const countRocketLuxuryProducts = countRocketName(products, "로켓럭셔리");
  const countRocketFreshProducts = countRocketName(products, "로켓프레시");
    // 가격에 콤마를 제거하고 숫자로 변환하는 함수
  const removeCommaAndParseInt = (priceString: string) => {
    return parseInt(priceString.replace(/,/g, ''));
  };


  // 전체 상품 대비 비율을 계산하는 함수 
  const getRateTotalProductsByTarget = (target: number) => {
    const total = products.length;
    return (target / total).toFixed(2);
  }
  // 제품들의 가격을 숫자로 변환하여 최대값과 최소값을 구하는 함수
  const findMinMaxPrice = (products: CoupangProductItem[]) => {
    const prices = products.map((product) => removeCommaAndParseInt(product.salePrice));
    const maxPrice = Math.max(...prices);
    const minPrice = Math.min(...prices);
    return { maxPrice, minPrice };
  };
  const { maxPrice, minPrice } = findMinMaxPrice(products);
  // 리뷰수를 나누는 함수 
    const countReviewGroups = (products: CoupangProductItem[]) => {
    let review0 = 0;
    let review1to49 = 0;
    let review50to100 = 0;
    let review100plus = 0;

    products.forEach((product) => {
      const reviewCount = parseInt(product.reviewCount.replace(/[^\d]/g, '')); // 숫자만 추출
      if (reviewCount === 0) {
        review0++;
      } else if (reviewCount >= 1 && reviewCount <= 49) {
        review1to49++;
      } else if (reviewCount >= 50 && reviewCount <= 100) {
        review50to100++;
      } else {
        review100plus++;
      }
    });

    return { review0, review1to49, review50to100, review100plus };
  };
  // 리뷰수 구함
    const { review0, review1to49, review50to100, review100plus } = countReviewGroups(products);
  //전체 쿠팡 갯수
  const countTotalRocket = (totalProductsCount - countRegularProducts);
  //특정 배송일 이상의 데이터 갯수를 구하는 함수 
  const countDateDifferenceOver = (products: CoupangProductItem[]) => {
  let count = 0;
  const currentDate = new Date();

  products.forEach((product) => {
    const deliveryDateStr = product.deliveryInfo;
    const dateRegex = /(\d{1,2})\/(\d{1,2})/; // 정규식 패턴: MM/dd 형식

    if (deliveryDateStr) {
      if (deliveryDateStr.includes("도착 예정") && dateRegex.test(deliveryDateStr)) {
        // "MM/dd 도착 예정" 형식인 경우
        const [, monthStr, dayStr] = deliveryDateStr.match(dateRegex)!;
        const month = parseInt(monthStr);
        const day = parseInt(dayStr);
        const targetDate = new Date(currentDate.getFullYear(), month - 1, day);

        if (targetDate.getTime() - currentDate.getTime() >= 10 * 24 * 60 * 60 * 1000) {
          count++;
        }
      } else if (deliveryDateStr.includes("내일")) {
        // "내일"이 포함된 경우 현재 날짜에 1일을 더하여 비교
        const tomorrow = new Date();
        tomorrow.setDate(currentDate.getDate() + 1);

        if (tomorrow.getDate() - currentDate.getDate() >= 10) {
          count++;
        }
      }
    }
  });

  return count;
};

  const deliveryLongDate = countDateDifferenceOver(products);
  const returnsData = {
    keyword,
    totalProductsCount,
    countRegularProducts,
    rateCountRegularProducts : 
    getRateTotalProductsByTarget(countRegularProducts),
    countRocketProducts,
    rateCountRocketProducts :  
    getRateTotalProductsByTarget(countRocketProducts),
    countSellerRocketProducts,
    rateCountSellerRocketProducts: 
    getRateTotalProductsByTarget(countSellerRocketProducts),
    countRocketDirectProducts,
    rateCountRocketDirectProducts:
    getRateTotalProductsByTarget(countRocketDirectProducts),
    countRocketInstallProducts,
    rateCountRocketInstallProducts:
    getRateTotalProductsByTarget(countRocketInstallProducts),
    countRocketLuxuryProducts,
    rateCountRocketLuxuryProducts:
    getRateTotalProductsByTarget(countRocketLuxuryProducts),
    countRocketFreshProducts,
    rateCountRocketFreshProducts:
      getRateTotalProductsByTarget(countRocketFreshProducts),
    countTotalRocket: countTotalRocket,
    rateCountTotalRocket : getRateTotalProductsByTarget(countTotalRocket),
    maxPrice,
    minPrice,
    avePrice: (maxPrice + minPrice) / 2,
    review0,
    review1to49,
    review50to100,
    review100plus,
    deliveryLongDate,
    rateDeliveryLongDate : getRateTotalProductsByTarget(deliveryLongDate)
  }
  return returnsData;
}