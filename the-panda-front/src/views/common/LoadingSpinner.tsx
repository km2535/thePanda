import React from 'react';
import { HashLoader } from 'react-spinners';

 const LoadingSpinner = ({isLoading}:any) => {
     return(
       <>{isLoading &&
         <HashLoader color="#001C34" loading={isLoading} />
        }
       </>
    )
  } 

 export default LoadingSpinner;