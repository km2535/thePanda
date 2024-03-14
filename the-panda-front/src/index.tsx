import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {  RouterProvider, createBrowserRouter } from 'react-router-dom';
import Landing from 'views/landing';
import SignUp from 'views/Authentication/signUp';
import RealTimeDataManager from 'views/admin/realTimedataManager';

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