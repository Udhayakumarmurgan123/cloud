// import React from 'react'
// const Greek  = () =>
// {
//     return React.createElement
//     ('div',
//         {id: 'hello',className: "dummyclasss"},React.createElement('h1',null,'Hello udhaya'))
// }
// export default Greek 
// import React from 'react'



// function Greek() {
//     constructor(props) {
//         super(props)
      
//         this.state = {
//            message : 'Hello world'
//         }
//       }
//       clickdemo()
//       {
//           this.setState (
//               {
//                   message : "Welcome Guys Good MOrning"
//               }
//           )
//       }
//   return (
//     <div>
//       <butto onclick = {() => clickdemo()}>Welcome Click me</butto>
//     </div>
//   )
// }

// export default Greek
import React, { Component } from 'react'

class Greek extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         count : 0
      }
    }
    increment = () =>
    {
        this.setState(
        {
            count : this.state.count + 1
        }
        )
    }
    decrement = () =>
    {
        this.setState(
            {
                count: this.state.count - 1 
            }
        )
    }
    reset = ()=>
    {
        this.setState(
            {
                count :  0
            }
        )
    }
    
    
  render() {
    return (
      <div>
        <center>
        <h2>{this.state.count}</h2>
        <button onClick={() => {this.increment()}}>Increment</button>
        <button onClick = { () => (this.reset())}>Reset</button>
        <button onClick = { () => {this.decrement()}}>Decrement</button>
        <h1>Current count : {this.state.count}</h1>
        </center>
      </div>
    )
  }
}

export default Greek

