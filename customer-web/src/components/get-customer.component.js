import React, { useState } from 'react';

function CustomerDetails() {
  const [customerId, setCustomerId] = useState('');
  const [customer, setCustomer] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const fetchCustomerById = async () => {
    setIsLoading(true);

    try {
      const response = await fetch(`/api/customers/${customerId}`);
      if (response.ok) {
        const customerData = await response.json();
        setCustomer(customerData);
      } else {
        // Handle error response
        console.error('Failed to fetch customer');
      }
    } catch (error) {
      // Handle fetch error
      console.error('Error fetching customer:', error);
    }

    setIsLoading(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchCustomerById();
  };

  return (
    <div>
      <h2>Customer Details</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Enter Customer ID:
          <input
            type="text"
            value={customerId}
            onChange={(e) => setCustomerId(e.target.value)}
          />
        </label>
        <button type="submit">Fetch Customer</button>
      </form>

      {isLoading ? (
        <p>Loading customer details...</p>
      ) : customer ? (
        <div>
          <p>ID: {customer.id}</p>
          <p>Name: {customer.name}</p>
          <p>Address: {customer.address}</p>
          <p>Number: {customer.number}</p>
          <p>Country Code: {customer.countryCode}</p>
          <p>Country Name: {customer.countryName}</p>
          <p>Carrier: {customer.carrier}</p>
        </div>
      ) : null}
    </div>
  );
}

export default CustomerDetails;
