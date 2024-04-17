import { deleteTrackingRank, getTrackingRank, trackingRankSave } from "apis/request/tracking";
import { useAuth } from "component/context/AuthContext";
import RankingTrackerCoupang from "component/ranking/coupang";
import RankingTrackerNaver from "component/ranking/naver";
import { useEffect, useState } from "react";

const TrackingRank = () => {
  const { userInfo } = useAuth();
  const [trackerResult, setTrackerResult] = useState();
  const trackingHistory = (e:any) => {
    // 저장함수 실행 후 데이터 불러오는 함수 실행
    trackingRankSave({
      id: e.target[1].value,
      userId: userInfo?.userId ?? "",
      searchKeyword: e.target[2].value,
      productId: e.target[3].value, 
      searchSource: e.target[4].value,
      searchType: e.target[4].value ==="coupang" ? e.target[5].value:e.target[4].value }).then(()=>getTrackingRank(userInfo?.userId ?? "").then((data)=>setTrackerResult(data.data)))
  } 
  useEffect(() => {
    getTrackingRank(userInfo?.userId ?? "").then((data)=>setTrackerResult(data.data))
  }, [userInfo?.userId])
  const eventHandler = (e: any,id: any) => {
    if (e.target.innerHTML === "삭제") { 
      deleteTrackingRank(id).then(() =>  getTrackingRank(userInfo?.userId ?? "").then((data)=>setTrackerResult(data.data)))
    }
  }

     return(
       <>
         <div>       
           <div>
             <RankingTrackerNaver trackingHistory={trackingHistory} trackerResult={trackerResult} eventHandler={eventHandler} />
             <RankingTrackerCoupang trackingHistory={trackingHistory} trackerResult={trackerResult} eventHandler={eventHandler} />
           </div>
        </div>
       </>
    )
  } 

 export default TrackingRank;