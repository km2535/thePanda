
export interface ButtonIconType{
  categoryId: string,
  iconImagePath: string,
  category: string,
  isActive: boolean,
  setCategoryTypeForRealTimeByCall : any
}
const ButtonInIcons = (props: ButtonIconType) => {
  
  return (
    <div  className={props.isActive ? " text-black font-bold flex flex-col cursor-pointer w-[95px] h-[120px] shadow drop-shadow-2xl rounded-lg": 'hover:text-black text-brandFontColor flex flex-col cursor-pointer w-[95px] h-[120px] hover:shadow drop-shadow-xl rounded-lg'} >
      <div className={' w-full h-[90px] '} >
        <div className={'w-full h-[80px]'}>
          <div onClick={props.setCategoryTypeForRealTimeByCall} id={props.categoryId}   className={`${props.iconImagePath} bg-no-repeat bg-center w-full float-none	h-full bg-contain z-[-15]`}></div>
        </div>
      </div>
      <div onClick={props.setCategoryTypeForRealTimeByCall} id={props.categoryId}  className={'h-full items-center flex justify-center  hover:text-inherit text-sm text-center'} >{props.category}</div>
    </div>
  )
}

export default ButtonInIcons;