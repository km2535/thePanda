import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import SignUp from './views/Authentication/signUp';
import OAuth from 'views/Authentication/OAuth';

function App() {
  return (
    <Routes>
      <Route path='/auth'>
        <Route path='sign-up' element={<SignUp/>}></Route>
        <Route path='oauth-response/:token/:expirationTime' element={<OAuth/>}></Route>
     </Route>
    </Routes>
  );
}

export default App;