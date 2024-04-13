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
import AnalysisProductToCoupang from 'views/analysis';
import TrackingRank from 'views/tracking';
import OAuth from 'views/Authentication/OAuth';



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
          { path: "related", element: <Relative/> }
          
        ]
      },
      {
        path:"analysis-coupang/product", element:<AnalysisProductToCoupang/>
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