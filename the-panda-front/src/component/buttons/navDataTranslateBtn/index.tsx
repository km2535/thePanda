
export interface SearchTypeButtonInfoDTO{
  buttonName: string,
  typeName : string,
  setType: React.Dispatch<React.SetStateAction<string>>,
  isActive: boolean,
  realTimeSearchHandler: any
}
const NaviButtonForDataTranslation = (props: SearchTypeButtonInfoDTO) => {
  const searchTypeHandler = (e:any) => {
    props.realTimeSearchHandler(e)
    props.setType(props.typeName);
  } 
  return (
    <>
      <div className={props.isActive ? "text-slate-950 font-semibold":"text-brandFontColor"}>
        <span id={props.typeName} className={"cursor-pointer text-2xl "} onClick={searchTypeHandler} >{props.buttonName}</span>
      </div>
    </>
  )
}

export default NaviButtonForDataTranslation;

