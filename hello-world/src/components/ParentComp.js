import React, { Component } from 'react'
import RegComp from './RegComp'
import PureComp from './PureComp'
import Memocomp from './Memocomp'

 class ParentComp extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         name : 'vishwas'
      }
    }
    componentDidMount()
    {
        setInterval( () => {
            this.setState({
                name : 'Udhay'
            })
        },2000)
    }
  render() {
    console.log("*********************Parent Component Render*************************")
    return (
      <div>
        Parent Component 
        <Memocomp name = {this.state.name}></Memocomp>
        {/* <RegComp name={this.state.name}></RegComp>
        <PureComp name={this.state.name}></PureComp> */}
      </div>
    )
  }
}

export default ParentComp
