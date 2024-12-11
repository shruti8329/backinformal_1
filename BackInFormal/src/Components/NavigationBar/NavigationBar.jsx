import React, { useEffect, useState } from 'react';
import './navbar.css';
import logo from './image.png';
import { Row, Col, Card, Button } from 'react-bootstrap';
import { NavLink } from 'react-router-dom';
import { useAuth } from '../Contexts/AuthContext';
import { toast } from 'react-toastify';
import createLogo from '../../assets/createInvoice.png';
import ListIcon from '../../assets/ListIcon.png';
import settingLogo from '../../assets/settingLogo.png';
import logoutIcon from '../../assets/logoutIcon.png';
import axios from 'axios';

function NavigationBar({ navTitle, setNavTitle, settings }) {
  const [businessNameOnNav, setBusinessNameOnNav] = useState('Business Name')

  const { logout } = useAuth();


  useEffect(() => {
    const setNameOfBizzzz = () => {
      if (settings) {
        const compName = settings.settingMaster.companyName
        if (compName.length <= 16) {
          setBusinessNameOnNav(compName)
        } else {
          setBusinessNameOnNav(compName.substring(0, 12) + '...')
        }
        // setBusinessNameOnNav(compName)
      }
    }
    setNameOfBizzzz()

  },[settings])
  return (
    <div className="navbarDiv">
      <Row>
        <Col xs={10}>
          <Row>
            <h3 style={{ marginLeft: '10px' }}>{navTitle}</h3>
          </Row>
          <Row>
            <Col xs={2}>
              <NavLink to="/dashboard/" className="nav-link">
                <Button
                  className="navCreateInvoiceBtn"
                  name="createInvoice"
                  onClick={() => setNavTitle('Create Invoice')}
                >
                  <img
                    src={createLogo}
                    alt=""
                    style={{ width: '20px', marginRight: '5px' }}
                  />{' '}
                  Create Invoice
                </Button>
              </NavLink>
            </Col>
            <Col xs={2}>
              <NavLink to="/dashboard/listinvoice" className="nav-link">
                <Button
                  className="navListInvoiceBtn"
                  name="listInvoice"
                  onClick={() => setNavTitle('List Invoice')}
                >
                  <img
                    src={ListIcon}
                    alt=""
                    style={{ width: '20px', marginRight: '10px' }}
                  />
                  List Invoice
                </Button>
              </NavLink>
            </Col>
            <Col xs={2}>
              <NavLink to="/dashboard/settings" className="nav-link">
                <Button
                  className="navBusinessSettingBtn"
                  name="settingPage"
                  onClick={() => setNavTitle('Business Setting')}
                >
                  <img
                    src={settingLogo}
                    alt=""
                    style={{ width: '20px', marginRight: '5px' }}
                  />
                  Business Setting
                </Button>
              </NavLink>
            </Col>
            <Col xs={2}>
              <Button
                className="navLogoutBtn"
                name="logoutSystem"
                onClick={logout}
              >
                <img
                  src={logoutIcon}
                  alt=""
                  style={{ width: '18px', marginRight: '10px' }}
                />
                Logout
              </Button>
            </Col>
          </Row>
        </Col>
        <Col xs={2}  style={{textAlign:'center'}} >
          <img
            src={
              settings
                ? `/apiClient/api/business-logo?businessLogo=${settings.settingMaster.logoImage}`
                : logo
            }
            style={{ borderRadius: '999px' }}
            alt="Logo"
            className="logo"
          />
          <div style={{ textAlign: 'center' }}><h5 className='bizzTitleInNavBar'>{businessNameOnNav}</h5></div>

        
        </Col>
      </Row>
      {/* <hr /> */}
    </div>
  );
}

export default NavigationBar;
