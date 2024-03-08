import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {  RouterProvider, createBrowserRouter } from 'react-router-dom';
import Landing from 'views/landing';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    //errorElement: <NotFound />,
    children: [
      { index: true, path: "/", element: <Landing /> }, //index로 '/' 메인페이지 지정
    ],
  },
]);
root.render(
  <React.StrictMode>
       <RouterProvider router={router} />
  </React.StrictMode>
);