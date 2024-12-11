import React, { useState } from 'react';
import { Routes, Route, Outlet } from 'react-router-dom';
import NavigationBar from '../NavigationBar/NavigationBar';
import ListInvoice from '../Invoice/ListInvoice';
import CreateInvoice from '../Invoice/CreateInvoiocePage';
import SettingMaster from '../SettingMaster/SettingMaster';
import EditInvoice from '../Invoice/EditInvoice';

const Dashboard = ({ settings, fetchSettings }) => {
  const [navTitle, setNavTitle] = useState('Create Invoice');

  return (
    <>
      <div style={{ overflowX: 'hidden' }}>
        <NavigationBar
          navTitle={navTitle}
          setNavTitle={setNavTitle}
          settings={settings}
        />
        <Routes>
          <Route path="/" element={<CreateInvoice settings={settings} />} />
          <Route
            path="/listinvoice"
            element={<ListInvoice settings={settings} />}
          />
          <Route
            path="/settings"
            element={
              <SettingMaster
                settings={settings}
                fetchSettings={fetchSettings}
              />
            }
          />
          <Route
            path="/editinvoice/:invoiceId"
            element={
              <EditInvoice settings={settings} setNavTitle={setNavTitle} />
            }
          />
        </Routes>
        <Outlet />
      </div>
    </>
  );
};

export default Dashboard;
