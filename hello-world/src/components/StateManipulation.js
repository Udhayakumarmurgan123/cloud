import React, { Component } from 'react'

export class StateManipulation extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         message : 'OFF'
      }
    }
    togglemessage = () =>
    {
        this.setState (prevstate =>({
            message : prevstate.message === 'OFF' ? 'ON' : 'OFF' 

        }))
    }
    
  render() {
    return (
      <div>
        <center>
        <h2>{this.state.message}</h2>
        <button onClick={()=> (this.togglemessage())}>click me</button>
        </center>
      </div>
    )
  }
}

export default StateManipulation
