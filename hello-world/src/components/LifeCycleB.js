import React, { Component } from 'react'

class LifeCycleB extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         name : 'Udhay'
      }
      console.log('LifeCycleB')
    }
    static getDerivedStateFromProps(state,props)
    {
        console.log('LifecycleB');
    }
componentDidMount ()
{
    console.log('LifeCycleB')
}
    
  render() {
    return (
      <div>
        <center>
        <div>LifecycleB</div>
        </center>
        
      </div>
    )
  }
}

export default LifeCycleB
