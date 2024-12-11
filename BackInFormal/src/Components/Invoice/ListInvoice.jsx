import React, { useEffect, useState } from 'react';
import { Table, Row, Col, Form, Pagination } from 'react-bootstrap';
import printIcon from '../../assets/printIcon.png';
import editIcon from '../../assets/editIcon.png';
import deleteIcon from '../../assets/deleteIcon.png'; // Import deleteIcon
import axios from 'axios';
import './invoicelist.css';
import printHelp from './PrintInvoice';
import { useNavigate } from 'react-router-dom'; 
import { toast } from 'react-toastify';

function ListInvoice({ settings }) {
  const [invoiceList, setInvoiceList] = useState([]);
  const [filteredInvoiceList, setFilteredInvoiceList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);

  

  const navigate = useNavigate(); 
  const fetchInvoices = async () => {
    try {
      const response = await axios.get('/apiClient/api/invoice/invoice-list');
      setInvoiceList(response.data);
      setFilteredInvoiceList(response.data);
    } catch (error) {
      console.error('Error fetching invoices:', error);
    }
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${month}-${day}-${year}`;
  };

  useEffect(() => {
    fetchInvoices();
  }, []);

  useEffect(() => {
    filterInvoices();
  }, [searchTerm, startDate, endDate, invoiceList]);

  const filterInvoices = () => {
    let filtered = invoiceList;

    if (searchTerm) {
      filtered = filtered.filter(
        (invoice) =>
          invoice.custName.toLowerCase().includes(searchTerm.toLowerCase()) ||
          invoice.uniqueInvoiceNumber.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    if (startDate || endDate) {
      const start = new Date(startDate);
      const end = new Date(endDate);
      filtered = filtered.filter((invoice) => {
        const invoiceDate = new Date(invoice.createdOn);
        return (!startDate || invoiceDate >= start) &&
               (!endDate || invoiceDate <= end);
      });
    }

    setFilteredInvoiceList(filtered);
  };

  const handleDeleteInvoiceById = async (id) => {
    const confirmed = window.confirm(`Delete invoice permanently?`);
    if (confirmed) {
      try {
        const response = await axios.delete(`/apiClient/api/invoice/${id}`);
        if (response.status === 200) {
          fetchInvoices();
          toast.success(`Invoice deleted successfully`);
        }
      } catch {
        toast.error(`Error deleting invoice`);
      }
    }
  };

  const handlePrintInvoice = async (invoiceId) => {
    const response = await axios.get(`/apiClient/api/invoice/${invoiceId}`);
    const invoice = response.data;

    const billedForData = {
      companyName: invoice.customer.custName,
      gstNo: invoice.customer.custGSTIN,
      email: invoice.customer.custEmail,
      address: invoice.customer.custAddress,
      panNo: invoice.customer.custPAN,
      phone: invoice.customer.custMobile,
      invoiceNo: invoice.uniqueInvoiceNumber,
      paidAmount: invoice.amtReceived.toFixed(2),
      dueAmount: invoice.amtUnpaid.toFixed(2),
      remarkNote: invoice.remarkNote,
    };
    const invoiceStat = {
      subTotal: invoice.subTotal,
      netTotal: invoice.netTotal,
      taxAmount: (invoice.netTotal - invoice.subTotal).toFixed(2),
    };
    printHelp(
      billedForData,
      invoice.invoiceListId.itemDataList,
      invoiceStat,
      settings
    );
  };

  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = filteredInvoiceList.slice(indexOfFirstItem, indexOfLastItem);

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const totalAmt = filteredInvoiceList.reduce(
    (acc, row) => acc + row.netTotal,
    0
  );
  const receivedAmt = filteredInvoiceList.reduce(
    (acc, row) => acc + row.amtReceived,
    0
  );
  const amtUnpaid = filteredInvoiceList.reduce(
    (acc, row) => acc + row.amtUnpaid,
    0
  );

  const totalPages = Math.ceil(filteredInvoiceList.length / itemsPerPage);

  const paginationItems = [];
  const maxPagesToShow = 5;
  const startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
  const endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

  if (totalPages > 1) {
    if (startPage > 1) {
      paginationItems.push(
        <Pagination.Ellipsis key="start-ellipsis" />
      );
    }

    for (let page = startPage; page <= endPage; page++) {
      paginationItems.push(
        <Pagination.Item
          key={page}
          active={page === currentPage}
          onClick={() => handlePageChange(page)}
        >
          {page}
        </Pagination.Item>
      );
    }

    if (endPage < totalPages) {
      paginationItems.push(
        <Pagination.Ellipsis key="end-ellipsis" />
      );
    }
  }

  return (
    <div className="mainDivForListInvicePage">
      <div className="filterSection">
        <Row className="mb-3">
          <Col xs={1}></Col>
          <Col xs={1}>
            <Form.Group controlId="exampleForm.SelectCustom" style={{ width: '70px' }}>
              <Form.Control as="select" onChange={(e)=>setItemsPerPage(e.target.value)} >
                <option value="">Rows</option>
                <option value={5}>5</option>
                <option value={10}>10</option>
                <option value={20}>20</option>
                <option value={30}>30</option>
                <option value={40}>40</option>
                <option value={50}>50</option>
              </Form.Control>
            </Form.Group>
          </Col>
          <Col xs={4}>
            <Form.Control
              type="text"
              placeholder="Search by Customer Name or Invoice Number"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </Col>
          <Col xs={2}>
            <Form.Control
              type="date"
              placeholder="Start Date"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
            />
          </Col>
          <Col xs={2}>
            <Form.Control
              type="date"
              placeholder="End Date"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
            />
          </Col>
        </Row>
        <Row>
          <Col xs={12}>
            <Table bordered hover responsive>
              <thead>
                <tr style={{ textAlign: 'center' }}>
                  <th>Total Invoices</th>
                  <th>Total Amount</th>
                  <th>Received Amount</th>
                  <th>Due Amount</th>
                </tr>
              </thead>
              <tbody>
                <tr style={{ textAlign: 'center' }}>
                  <td><strong>{filteredInvoiceList.length}</strong></td>
                  <td style={{ color: 'blue' }}> <strong> {totalAmt.toFixed(2)}</strong></td>
                  <td style={{ color: 'green' }}><strong>{receivedAmt.toFixed(2)}</strong></td>
                  <td style={{ color: 'red' }}><strong>{amtUnpaid.toFixed(2)}</strong></td>
                </tr>
              </tbody>
            </Table>
          </Col>
        </Row>
      </div>

      {filteredInvoiceList.length > 0 ? (
        <div className="listInvoiceDiv" style={{height:'50vh'}}>
          <Table bordered hover responsive className="tableOfInvoiceItem">
            <thead >
              <tr>
                <th style={{ width: '10%' }}>Invoice Id</th>
                <th style={{ width: '20%' }}>Customer Name</th>
                <th style={{ width: '15%', textAlign: 'center' }}>Date(MM-DD-YYYY)</th>
                <th style={{ width: '10%' }}>Total</th>
                <th style={{ width: '12%' }}>Received Amount</th>
                <th style={{ width: '10%' }}>Due Amount</th>
                <th style={{ width: '5%' }}>Print</th>
                <th style={{ width: '5%' }}>Edit</th>
                <th style={{ width: '5%' }}>Delete</th>
              </tr>
            </thead>
            <tbody>
              {currentItems.map((data) => (
                <tr key={data.uniqueInvoiceNumber}>
                  <td>{data.uniqueInvoiceNumber}</td>
                  <td>{data.custName}</td>
                  <td style={{ textAlign: 'center' }}>{formatDate(data.createdOn)}</td>
                  <td>{data.netTotal.toFixed(2)}</td>
                  <td>{data.amtReceived.toFixed(2)}</td>
                  <td>{data.amtUnpaid.toFixed(2)}</td>
                  <td
                    style={{ textAlign: 'center' }}
                    onClick={() => handlePrintInvoice(data.uniqueInvoiceNumber)}
                  >
                    <img
                      src={printIcon}
                      alt="Print"
                      style={{ width: '20px', marginRight: '10px', cursor: 'pointer' }}
                    />
                  </td>
                  <td
                    style={{ textAlign: 'center' }}
                    onClick={() => handleEditInvoice(data.uniqueInvoiceNumber)}
                  >
                    <img
                      src={editIcon}
                      alt="Edit"
                      style={{ width: '20px', marginRight: '10px', cursor: 'pointer' }}
                    />
                  </td>
                  <td
                    style={{ textAlign: 'center' }}
                    onClick={() =>
                      handleDeleteInvoiceById(data.uniqueInvoiceNumber)
                    }
                  >
                    <img
                      src={deleteIcon}
                      alt="Delete"
                      style={{ width: '20px', marginRight: '10px', cursor: 'pointer' }}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <Row>
            <Col xs={9}></Col>
            <Col xs={3}>
              <Pagination>
                <Pagination.Prev
                  onClick={() =>
                    setCurrentPage((prevPage) => Math.max(prevPage - 1, 1))
                  }
                  disabled={currentPage === 1}
                />
                {paginationItems}
                <Pagination.Next
                  onClick={() =>
                    setCurrentPage((prevPage) => Math.min(prevPage + 1, totalPages))
                  }
                  disabled={currentPage === totalPages}
                />
              </Pagination>
            </Col>
          </Row>
        </div>
      ) : (
        <h3>No Invoices Found</h3>
      )}
    </div>
  );
}

export default ListInvoice;
