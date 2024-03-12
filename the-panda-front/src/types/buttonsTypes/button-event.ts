import { FormEventHandler } from "react"

export interface handler{
  inputEventHandler: React.MouseEventHandler<HTMLInputElement>,
}

export interface handlerWithButtonName extends handler{
  buttonName : string
}

export interface handlerForm {
  inputEventHandler : FormEventHandler<HTMLFormElement>
}