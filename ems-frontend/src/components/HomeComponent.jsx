import { useNavigate } from 'react-router-dom';

const HomeComponent = () => {
  const navigate = useNavigate();

  return (
    <div className="container py-5">
      <h1 className="text-center mb-5">Employee Management System</h1>
      <div className="row justify-content-center">
        <div className="col-md-4 mb-4">
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
        <div className="col-md-4 mb-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body text-center">
              <h3 className="card-title mb-4">Department Management</h3>
              <p className="card-text">Organize and manage departments, assign employees, and track department structures.</p>
              <button 
                className="btn btn-primary btn-lg"
                onClick={() => navigate('/departments')}
              >
                View Departments
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-4 mb-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body text-center">
              <h3 className="card-title mb-4">Task Management</h3>
              <p className="card-text">Manage tasks and assignments for your employees, track progress and deadlines.</p>
              <button 
                className="btn btn-primary btn-lg"
                onClick={() => navigate('/tasks')}
              >
                View Tasks
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomeComponent;