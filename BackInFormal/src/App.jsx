import react, { useEffect, useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Dashboard from './Components/Dashboard/Dashboard';
import { AuthProvider, useAuth } from './Components/Contexts/AuthContext';
import {
  Navigate,
  Route,
  BrowserRouter as Router,
  Routes,
} from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Login from './Components/Login/Login';
import axios from 'axios';
import ForgetPassword from './Components/Login/ForgetPassword';

const PrivateRoute = ({ element, ...rest }) => {
  const { user } = useAuth();
  return user != null ? element : <Navigate to="/" />;
};

function App() {
  const [settings, setSettings] = useState(null);

  const fetchSettings = async () => {
    const response = await axios.get('/apiClient/api/bankDetails/1');
    setSettings(response.data);
    console.log(response.data);
  };

  useEffect(() => {
    fetchSettings();
  }, []);

  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Login settings={settings} />} />
          <Route
            path="/forget"
            element={<ForgetPassword settings={settings} />}
          />

          <Route
            path="/dashboard/*"
            element={
              <PrivateRoute
                element={
                  <Dashboard
                    settings={settings}
                    fetchSettings={fetchSettings}
                  />
                }
              />
            }
          />
        </Routes>
        <ToastContainer
          position="top-center" // Set default position for all toasts
          autoClose={3500}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
        {' '}
        {/* Add ToastContainer here */}
      </Router>
    </AuthProvider>
  );
}

export default App;
