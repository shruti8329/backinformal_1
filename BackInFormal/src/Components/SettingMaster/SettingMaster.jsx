 import React from 'react'
import { Col, Row } from 'react-bootstrap'
import Bank_details from './BankMaster'
import BusinessDetails from './BusinessDetails'
 
 function SettingMaster({settings,fetchSettings}) {
   return (
     <div style={{marginTop:'20px'}}>
       <Row>
        <Col xs={6}>
        
        <Bank_details settings={settings} fetchSettings={fetchSettings} />
        </Col>
        <Col xs={6}> 
        <BusinessDetails settings={settings} fetchSettings={fetchSettings} />
        </Col>

       </Row>
     </div>
   )
 }
 
 export default SettingMaster
 