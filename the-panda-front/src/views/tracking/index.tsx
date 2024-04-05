import RankingTrackerCoupang from "component/ranking/coupang";
import RankingTrackerNaver from "component/ranking/naver";

const TrackingRank = () => {
  
  
     return(
       <>
         <div>
           
           <div>
             <RankingTrackerNaver />
             <RankingTrackerCoupang/>
           </div>
        </div>
       </>
    )
  } 

 export default TrackingRank;