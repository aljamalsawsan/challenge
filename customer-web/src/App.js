import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddCustomer from "./components/add-customer.component";
import CustomersList from "./components/get-customer.component";
import DeleteCustomer from "./components/delete-customer.component";
import UpdateCustomer from "./components/update-customer.component";


class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/customers"} className="navbar-brand">
            Areeba Challenge        
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/customers"} className="nav-link">
                Get Customer
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Add
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/update"} className="nav-link">
                Update
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/delete"} className="nav-link">
                Delete
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<CustomersList/>} />
            <Route path="/customers" element={<CustomersList/>} />
            <Route path="/add" element={<AddCustomer/>} />
            <Route path="/delete" element={<DeleteCustomer/>} />
            <Route path="/update" element={<UpdateCustomer/>} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;