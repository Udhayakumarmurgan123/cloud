import React, { Component } from 'react'
import ScopedStyles from './ScopedStyles'
class MouseOver extends Component {
  constructor(props) {
    super(props)
  
    this.state = {
      isHovered : false  ,
       buttontext:'Hover Me!'
    }
  }
  handleMouseEnter = () =>
  {
    this.setState(
      {
        isHovered : true ,
        buttontext:'Hello Iam Hovered!, Good afternoon'
      }
    );
  }
  handleMouseLeave =()=>
  {
    this.setState(
      {
        isHovered : false ,
        buttontext:'Hello Iam Hovered!'
      }
    )
  }
  
  render() {
    {
      const buttonStyle = {
        padding : '10px 20px',
        fontsize : '16px',
        cursor : 'pointer',
        backgroundColor : this.state.isHovered? 'blue' : 'green',
        color : 'black',
        border : 'none',
        borderRadius : '5px'
      
    };
    return (
        <button 
        style={buttonStyle}
        onMouseEnter={this.handleMouseEnter}
         onMouseLeave={this.handleMouseLeave}
         >
         {this.state.buttontext}

         </button>
    );
  }
}

}

export default MouseOver
