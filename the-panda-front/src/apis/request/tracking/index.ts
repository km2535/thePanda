import axios from "axios"

export interface userTracker {
  id: string,
  userId: string,
  productId: string,
  searchKeyword: string,
  searchType: string,
  searchSource: string
}

export const trackingRankSave = async (data: userTracker) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/save-track`;
  await axios.post(API_URL, data)
}
export const getTrackingRank = async (userId: string) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/get-track?userId=${userId}`;
  return await axios.get(API_URL).then((data) => data)
}
export const deleteTrackingRank = async (id: any) => {
  const API_URL = `${process.env.REACT_APP_MAIN_API_DOMAIN}/api/panda-v1/delete-track`;
  await axios.delete(API_URL, {data:id})
}