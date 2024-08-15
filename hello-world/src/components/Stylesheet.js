import React from 'react'
import './mySheet.css'

function Stylesheet(props) {
    let className = props.primary ? 'primary' : " "
  return (
    <div>
      <h2 className={`${className} font-xl`}>StyleSheet in Javascript,...</h2>
    </div>
  )
}

export default Stylesheet
