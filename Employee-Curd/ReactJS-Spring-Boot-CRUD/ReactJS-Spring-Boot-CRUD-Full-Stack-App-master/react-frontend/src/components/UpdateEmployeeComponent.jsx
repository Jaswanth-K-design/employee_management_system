import React, { Component } from 'react'
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            Name: '',
            DOJ: '',
            emailId: '',
            Number:''
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailIdHandler = this.changeEmailIdHandler.bind(this);
        this.changeDOJHandler = this.changeDOJHandler.bind(this);
        this.changeNumberHandler = this.changeNumberHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then( (res) =>{
            let employee = res.data;
            this.setState({firstName: employee.Name,
                DOJ: employee.DOJ,
                emailId : employee.emailId,
                Number: employee.Number
            });
        });
    }

    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {Name: this.state.Name, DOJ: this.state.DOJ, emailId: this.state.emailId, Number:this.state.Number};
        console.log('employee => ' + JSON.stringify(employee));
        console.log('id => ' + JSON.stringify(this.state.id));
        EmployeeService.updateEmployee(employee, this.state.id).then( res => {
            this.props.history.push('/employees');
        });
    }
    
    changeNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeDOJHandler= (event) => {
        this.setState({DOJ: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({emailId: event.target.value});
    }
    changeNumberHandler= (event) => {
        this.setState({Number: event.target.value});
    }

    cancel(){
        this.props.history.push('/employees');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Employee</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Name: </label>
                                            <input placeholder="Name" name="Name" className="form-control" 
                                                value={this.state.Name} onChange={this.changeNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> DOJ: </label>
                                            <input placeholder="DOJ" name="DOJ" className="form-control" 
                                                value={this.state.DOJ} onChange={this.changeDOJHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Number: </label>
                                            <input placeholder="Number" name="Number" className="form-control" 
                                                value={this.state.Number} onChange={this.changeEmailHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.updateEmployee}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateEmployeeComponent
