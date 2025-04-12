import { useNavigate } from 'react-router-dom';

const HomeComponent = () => {
  const navigate = useNavigate();

  return (
    <div className="container py-5">
      <h1 className="text-center mb-5">Employee Management System</h1>
      <div className="row justify-content-center">
        <div className="col-md-5 mb-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body text-center">
              <h3 className="card-title mb-4">Employee Management</h3>
              <p className="card-text">Manage your employees' information, including personal details and contact information.</p>
              <button 
                className="btn btn-primary btn-lg"
                onClick={() => navigate('/employees')}
              >
                View Employees
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomeComponent;