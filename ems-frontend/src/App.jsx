
import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import ListTaskComponent from './components/ListTaskComponent'
import TaskComponent from './components/TaskComponent'
import HomeComponent from './components/HomeComponent'
import ListDepartmentComponent from './components/ListDepartmentComponent'
import DepartmentComponent from './components/DepartmentComponent'
import ListAttendanceComponent from './components/ListAttendanceComponent'
import AttendanceComponent from './components/AttendanceComponent'
import ListCheckInComponent from './components/ListCheckInComponent'
import CheckInComponent from './components/CheckInComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'

function App() {
  

  return (
    <>
    <BrowserRouter>
      <HeaderComponent />
      <Routes>
        <Route path='/' element = {<HomeComponent />}></Route>
        <Route path='/employees' element = {<ListEmployeeComponent />}></Route>
        <Route path='/add-employee'  element={<EmployeeComponent />}></Route>
        <Route path='/edit-employee/:id'  element={<EmployeeComponent />}></Route>
        {/* Task Management Routes */}
        <Route path='/tasks' element={<ListTaskComponent />}></Route>
        <Route path='/add-task' element={<TaskComponent />}></Route>
        <Route path='/edit-task/:id' element={<TaskComponent />}></Route>
        {/* Department Management Routes */}
        <Route path='/departments' element={<ListDepartmentComponent />}></Route>
        <Route path='/add-department' element={<DepartmentComponent />}></Route>
        <Route path='/edit-department/:id' element={<DepartmentComponent />}></Route>
        {/* Attendance Management Routes */}
        <Route path='/attendances' element={<ListAttendanceComponent />}></Route>
        <Route path='/add-attendance' element={<AttendanceComponent />}></Route>
        <Route path='/edit-attendance/:date' element={<AttendanceComponent />}></Route>
        <Route path='/view-attendance/:date' element={<AttendanceComponent />}></Route>
        {/* Check-In Management Routes */}
        <Route path='/check-ins' element={<ListCheckInComponent />}></Route>
        <Route path='/add-check-in' element={<CheckInComponent />}></Route>
      </Routes>
      
      <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
