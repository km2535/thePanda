import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import SignUp from './views/Authentication/signUp';
import OAuth from 'views/Authentication/OAuth';
import Landing from 'views/landing';

function App() {
  return (
    <Routes>
      <Route path='/auth'>
        <Route path='sign-up' element={<SignUp/>}></Route>
        <Route path='oauth-response/:token/:expirationTime' element={<OAuth/>}></Route>
      </Route>
      <Route path='/' element={<Landing/>}></Route>
    </Routes>
  );
}

export default App;