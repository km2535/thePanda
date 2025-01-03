import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {  RouterProvider, createBrowserRouter } from 'react-router-dom';
import Landing from 'views/landing';
import SignUp from 'views/Authentication/signUp';
import RealTimeDataManager from 'views/admin/realTimedataManager';
import Keyword from 'views/keyword';
import Search from 'views/keyword/search';
import Analysis from 'views/keyword/analysis';
import Relative from 'views/keyword/relative';
import AnalysisProductToCoupang from 'views/analysis/product';
import TrackingRank from 'views/tracking';
import OAuth from 'views/Authentication/OAuth';
import OptimizationKeyword from 'views/optimization';
import AnalysisMarket from 'views/analysis/market';
import MarketInNaver from 'views/analysis/market/naver';
import MarketInCoupang from 'views/analysis/market/coupang';
import MarketInGmarket from 'views/analysis/market/gmarket';
import MarketInKakao from 'views/analysis/market/kakao';



const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
const router = createBrowserRouter([
  {
    path: "/",
    element: <App/>,
    //errorElement: <NotFound />,
    children: [
      { index: true, path: "/", element: <Landing /> }, 
      {
        path: "keyword", element: <Keyword />, children: [
          { path: "search", element: <Search /> },
          { path: "analysis", element: <Analysis/> },
          { path: "related", element: <Relative /> },
          {path:"optimization", element: <OptimizationKeyword/>}
          
        ]
      },
      {
        path:"analysis-coupang/product", element:<AnalysisProductToCoupang/>
      },
      {
        path: "analysis/market", element: <AnalysisMarket />, children: [
          { path: "naver", element: <MarketInNaver /> },
          { path: "coupang", element: <MarketInCoupang/> },
          { path: "gmarket", element: <MarketInGmarket /> },
          { path: "kakao", element: <MarketInKakao /> },
          
        ]
      },
      {
        path:"product/tracking", element:<TrackingRank/>
      },
      {path:"admin", element:<RealTimeDataManager/>},
      { path: "auth/sign-up", element: <SignUp /> },
      {path:"auth/oauth-response/:token/:expirationTime/:userid", element:<OAuth/>}
    ],
  },
]);
root.render(   

      <RouterProvider router={router} />
);