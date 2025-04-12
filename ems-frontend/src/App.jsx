
import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import HomeComponent from './components/HomeComponent'
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
      </Routes>
      
      <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
