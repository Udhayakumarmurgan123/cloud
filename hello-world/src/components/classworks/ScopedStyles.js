import React, { Component } from 'react'

export class ScopedStyles extends Component {
  render() {
    const buttonStyle = 
    {
        background : 'black',
        padding : '10px20px',
        border  : 'blue',
        color : 'white',
        borderRadius : '5px',
        cursor : 'pointer'
    };

    const textStyle = 
    {
            fontSize : '18px',
            color : 'black',
            marginBotton : '10px' 
    }
    return (
     <div>
        <p style={buttonStyle}>This is Udhay</p>
        <p style={textStyle}>click Me</p>
     </div>
    )
  }
}

export default ScopedStyles
