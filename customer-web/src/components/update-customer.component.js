import React, { useState, useEffect } from 'react';

function UpdateCustomer() {
  const [customerId, setCustomerId] = useState('');
  const [customerData, setCustomerData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    address: '',
    number: '',
  });
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const fetchCustomerData = async () => {
    setIsLoading(true);

    try {
      const response = await fetch(`/api/customers/${customerId}`);
      if (response.ok) {
        const customer = await response.json();
        setCustomerData(customer);
        setFormData({
          name: customer.name,
          address: customer.address,
          number: customer.number,
        });
      } else {
        // Handle error response
        console.error('Failed to fetch customer data');
      }
    } catch (error) {
      // Handle fetch error
      console.error('Error fetching customer data:', error);
    }

    setIsLoading(false);
  };

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`/api/customers/${customerId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setSuccessMessage('Customer updated successfully');
        setErrorMessage('');
        console.log('Customer updated successfully');
      } else if (response.status === 500) {
        setErrorMessage('Invalid number');
        setSuccessMessage('');
      } else {
        // Handle error response
        console.error('Failed to update customer');
      }
    } catch (error) {
      // Handle fetch error
      console.error('Error updating customer:', error);
    }
  };

  return (
    <div>
      <h2>Update Customer</h2>
      <form onSubmit={e => { e.preventDefault(); fetchCustomerData(); }}>
        <label>
          Enter Customer ID:
          <input
            type="text"
            value={customerId}
            onChange={(e) => setCustomerId(e.target.value)}
          />
        </label>
        <button type="submit">Fetch Customer Data</button>
      </form>

      {isLoading ? (
        <p>Loading customer data...</p>
      ) : customerData ? (
        <form onSubmit={handleSubmit}>
          {successMessage && (
            <div className="alert alert-success">{successMessage}</div>
          )}
          {errorMessage && (
            <div className="alert alert-danger">{errorMessage}</div>
          )}

          <label>
            Name:
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Address:
            <input
              type="text"
              name="address"
              value={formData.address}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Number:
            <input
              type="text"
              name="number"
              value={formData.number}
              onChange={handleInputChange}
            />
          </label>
          <button type="submit">Update Customer</button>
        </form>
      ) : null}
    </div>
  );
}

export default UpdateCustomer;
