import React from 'react'
import Sidebar from './Sidebar';
import Poll1 from './Poll1';
import SidebarRight from './SidebarRight';



function Dashboard() {
  return (
    <div className='flex flex-row justify-between'>
      <Sidebar />
      <Poll1 />
      <SidebarRight />
    </div>
  )
}

export default Dashboard
