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