const getOneMonthAgo = (dateString:string)=> {
  const currentDate = new Date(dateString);
  const oneMonthAgo = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, currentDate.getDate());
  return oneMonthAgo.toISOString().split('T')[0];
}

export default getOneMonthAgo;