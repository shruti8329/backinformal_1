import React, { useState } from 'react';
import './login.css';
import { Row, Col, Card, Button } from 'react-bootstrap';
import LoginHomeImg from '../../assets/LoginHomeImg.png';
import axios from 'axios';
import { toast } from 'react-toastify';
import { useAuth } from '../Contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const forgetPass = () => {
    navigate('/forget');
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    // const formdata=new FormData();
    // formdata.append('username',username);
    // formdata.append('password',password);
    try {
      const response = await axios.post(
        '/apiClient/users/user-login',
        {
          userName: username,
          password: password,
        }
      );
      if (response.status == 200) {
        toast.success('Log in success...');
        login(response.data);
        navigate('/dashboard');
      }
    } catch (error) {
      console.log(error);

      toast.error(error.response.data);
    }
  };

  return (
    <div className="loginPageHOme">
      <Row className="loginPageHOme">
        <Col xs={8} className="imgFieldInLOgin">
          <img
            src={LoginHomeImg}
            alt=""
            style={{
              width: '800px',
            }}
          />
        </Col>
        <Col xs={4} className="formFieldInLogin">
          <Row className="loginFormInLOginPage">
            <Col xs={12}>
              <h2 style={{ color: '#db634a', textAlign: 'center' }}>Login</h2>
            </Col>
            <Col xs={12}>
              <form onSubmit={(e) => handleLogin(e)}>
                <label htmlFor="exampleInputEmail1">User Name</label>
                <br />
                <div className="formGRoup">
                  <input
                    type="text"
                    name=""
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>

                <label htmlFor="exampleInputEmail1">Password</label>
                <br />
                <div className="formGRoup">
                  <input
                    type="password"
                    name=""
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>

                <Button type="submit" className="LoginBtnInLoginPage">
                  Login
                </Button>
                <p
                  className="ForgotPasSpan"
                  style={{ cursor: 'pointer' }}
                  onClick={forgetPass}
                >
                  Forgot Password
                </p>
              </form>
            </Col>
          </Row>
        </Col>
      </Row>
    </div>
  );
}

export default Login;
