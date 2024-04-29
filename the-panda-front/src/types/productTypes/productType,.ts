export interface Product {
  productId : string;
  title : string;
  link : string;
  image : string;
  lprice : number;
  hprice : number;
  mallName : string;
  category1 : string;
  category2 : string;
  category3 : string;
  category4 : string;
  productType : string;
  maker : string;
  brand : string;
  keyword : string;
  rank : number;
}

export interface CoupangProduct{
  deliveryInfo : string,
  image : string,
  isAd : string,
  link : string,
  originalPrice : string,
  productId : string,
  productName: string,
  ranking: string,
  rating: string,
  reviewCount: string,
  rewqardInfo: string,
  rocket: string,
  rocketImgUrl: string,
  salePrice: string,
  vendorId : string,
  sortType : string,
}

export interface CoupangProducts {
  [category: string]: Array<CoupangProductItem>;
}

export interface CoupangProductItem {
  image: string;
  deliveryInfo: string;
  originalPrice: string;
  productId: string;
  salePrice: string;
  rocket: string;
  rating: string;
  vendorId: string;
  productName: string;
  rocketName: string;
  rocketImgUrl: string;
  reviewCount: string;
  rewardInfo: string;
  ranking: string;
  productUrl: string;
  isAd: string;
}


export interface ProductCoupangCardData {
  keyword: string;
  totalProductsCount: number;
  countRegularProducts: number;
  rateCountRegularProducts: string;
  countRocketProducts: number;
  rateCountRocketProducts: string;
  countSellerRocketProducts: number;
  rateCountSellerRocketProducts: string;
  countRocketDirectProducts: number;
  rateCountRocketDirectProducts: string;
  countRocketInstallProducts: number;
  rateCountRocketInstallProducts: string;
  countRocketLuxuryProducts: number;
  rateCountRocketLuxuryProducts: string;
  countRocketFreshProducts: number;
  rateCountRocketFreshProducts: string;
  countTotalRocket: number,
  rateCountTotalRocket : string,
  maxPrice: number;
  minPrice: number;
  avePrice: number;
  review0: number;
  review1to49: number;
  review50to100: number;
  review100plus: number;
  deliveryLongDate: number;
  rateDeliveryLongDate : string,
}





export interface GmarketProducts {
  [category: string]: Array<GmarketProductItem>;
}

export interface GmarketProductItem {
  originalPrice: string;
  reviewCount: string;
  salesPrice: string;
  freeDelivery: string;
  sellingCount: string;
  productName: string;
  DeliveryInfo: string;
  awardPoints: string;
  productUrl: string;
}


export interface ProductGmarketCardData {
  keyword: string;
  totalProductsCount: number;
  todayDeliveryCount: number;
  freeDeliveryCount: number;
  minSalesCount: number;
  aveSalesCount: number;
  maxSalesCount: number;
  satisfy50minus: number;
  satisfy50to60: number;
  satisfy60to70: number;
  satisfy70to80: number;
  satisfy80to90: number;
  satisfy90to100: number;
  maxPrice: number;
  minPrice: number;
  avePrice: number;
  review0: number;
  review1to49: number;
  review50to100: number;
  review100plus: number;
}


export interface KakaoProducts {
  [category: string]: Array<KakaoProductItem>;
}

export interface KakaoProductItem {
  productName: string;
  categoryName: string;
  deliveryFeeType: string;
  originalPrice: string;
  discountedPrice: string;
  sharingImageUrl: string;
  productPositivePercentage: string;
  storeName: string;
  reviewCount: string;
  groupDiscountUserCount: string;
  freeDelivery: string;
  newProduct: string;
  totalSaleCount: string;
  storeLinkPath: string;
}


export interface ProductKakaoCardData {
  keyword: string;
  totalProductsCount: number;
  totalNewProductsCount: number;
  freeDeliveryCount: number;
  minSalesCount: number;
  aveSalesCount: number;
  maxSalesCount: number;
  maxPrice: number;
  minPrice: number;
  avePrice: number;
  review0: number;
  review1to49: number;
  review50to100: number;
  review100plus: number;
  top1Sales: number;
  top1StoreName: string;
  top1storeLinkPath: string;
  top2Sales: number;
  top2StoreName: string;
  top2storeLinkPath: string;
  top3Sales: number;
  top3StoreName: string;
  top3storeLinkPath: string;
  top4Sales: number;
  top4StoreName: string;
  top4storeLinkPath: string;
  top5Sales: number;
  top5StoreName: string;
  top5storeLinkPath: string;
}

