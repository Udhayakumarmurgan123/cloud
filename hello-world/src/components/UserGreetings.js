import React, { Component } from 'react'

export class UserGreetings extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         isLoggedIn : false
      }
    }
    
  render() {
    // return (this.state.isLoggedIn && <div>welcome  udhay</div>)
    return ( this.state.isLoggedIn ? (<div>
        Welcome Udhay</div>) : (<div>Welcome Guest</div>))
   
    // let message 
    // if(this.state.isLoggedIn)
    // {
    //     return(<div>Welcome Udhay</div>)
    // }
    // else{
    //     return(<div>Welcome Guest</div>)
    // }
    // if(this.state.isLoggedIn)
    // {
    //     return (<div>welcome udhay</div>)
    // }
    // else
    // {
    //     return (<div>Welcome Guest</div>)
    // }

    // return (
    //   <div>
    //     <div>Welcome Udhay</div>
    //   </div>
    // )
  }
}

export default UserGreetings
