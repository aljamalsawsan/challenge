import React, { useState } from 'react';

function DeleteCustomer() {
  const [customerId, setCustomerId] = useState('');
  const [isDeleting, setIsDeleting] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');

  const handleDelete = async () => {
    setIsDeleting(true);

    try {
      const response = await fetch(`/api/customers/${customerId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setSuccessMessage('Customer deleted successfully');
        console.log('Customer deleted successfully');
      } else {
        // Handle error response
        console.error('Failed to delete customer');
      }
    } catch (error) {
      // Handle fetch error
      console.error('Error deleting customer:', error);
    }

    setIsDeleting(false);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleDelete();
  };

  return (
    <div>
      <h2>Delete Customer</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Enter Customer ID:
          <input
            type="text"
            value={customerId}
            onChange={(e) => setCustomerId(e.target.value)}
          />
        </label>
        <button type="submit" disabled={isDeleting}>
          {isDeleting ? 'Deleting...' : 'Delete Customer'}
        </button>
      </form>

      {successMessage && <div className="alert alert-success">{successMessage}</div>}
    </div>
  );
}

export default DeleteCustomer;
