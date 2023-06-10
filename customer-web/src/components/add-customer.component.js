import React, { Component } from "react";
import CustomerDataService from "../services/customer.service";

export default class AddCustomer extends Component {
  constructor(props) {
    super(props);
    this.onChangeNumber = this.onChangeNumber.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeAddress = this.onChangeAddress.bind(this);
    this.saveCustomer = this.saveCustomer.bind(this);
    this.newCustomer = this.newCustomer.bind(this);

    this.state = {
      id: null,
      number: "",
      name: "",
      address: "",
      successMessage: "",
      errorMessage: "",
      customerObject: null
    };
  }

  onChangeNumber(e) {
    this.setState({
      number: e.target.value,
      successMessage: "",
      errorMessage: "",
      customerObject: null
    });
  }

  onChangeName(e) {
    this.setState({
      name: e.target.value
    });
  }

  onChangeAddress(e) {
    this.setState({
      address: e.target.value
    });
  }

  saveCustomer() {
    var data = {
      number: this.state.number,
      name: this.state.name,
      address: this.state.address
    };

    CustomerDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          number: response.data.number,
          name: response.data.name,
          address: response.data.address,
          countryCode: response.data.countryCode,
          countryName: response.data.countryName,
          carrier: response.data.carrier,
          successMessage: "Customer added successfully",
          errorMessage: "",
          customerObject: response.data
        });
        console.log(response.data);
      })
      .catch(error => {
        if (error.response && error.response.status === 500) {
          this.setState({
            successMessage: "",
            errorMessage: "Invalid number",
            customerObject: null
          });
        } else {
          console.log(error);
        }
      });
  }

  newCustomer() {
    this.setState({
      id: null,
      number: "",
      name: "",
      address: "",
      successMessage: "",
      errorMessage: "",
      customerObject: null
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.successMessage && (
          <div className="alert alert-success" role="alert">
            {this.state.successMessage}
          </div>
        )}

        {this.state.errorMessage && (
          <div className="alert alert-danger" role="alert">
            {this.state.errorMessage}
          </div>
        )}

        {this.state.customerObject && (
          <div>
            <h4>Customer Added Successfully!</h4>
            <div>
              <p>ID: {this.state.customerObject.id}</p>
              <p>Number: {this.state.customerObject.number}</p>
              <p>Name: {this.state.customerObject.name}</p>
              <p>Address: {this.state.customerObject.address}</p>
              <p>Country Code: {this.state.countryCode}</p>
              <p>Country Name: {this.state.countryName}</p>
              <p>Carrier: {this.state.carrier}</p>
            </div>
          </div>
        )}

        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newCustomer}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="name">Name</label>
              <input
                type="text"
                id="name"
                required
                value={this.state.name}
                onChange={this.onChangeName}
                name="name"
              />
            </div>

            <div className="form-group">
              <label htmlFor="address">Address</label>
              <input
                type="text"
                id="address"
                required
                value={this.state.address}
                onChange={this.onChangeAddress}
                name="address"
              />
            </div>

            <div className="form-group">
              <label htmlFor="number">Number</label>
              <input
                type="text"
                id="number"
                required
                value={this.state.number}
                onChange={this.onChangeNumber}
                name="number"
              />
            </div>

            <button onClick={this.saveCustomer}>
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}
