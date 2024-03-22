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

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    //errorElement: <NotFound />,
    children: [
      { index: true, path: "/", element: <Landing /> }, 
      {
        path: "keyword", element: <Keyword />, children: [
          { path: "search", element: <Search /> },
          { path: "analysis", element: <Analysis/> },
          { path: "related", element: <Relative/> }
          
      ]},
      {path:"admin", element:<RealTimeDataManager/>},
      {
        path: "auth/sign-up", element: <SignUp/>}
    ],
  },
]);
root.render(
  <React.StrictMode>
       <RouterProvider router={router} />
  </React.StrictMode>
);