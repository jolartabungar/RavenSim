import React from 'react';
import Sidebar from '../Sidebar';
import Header from '../Header';
import SmartGrid from '../Grid/SmartGrid';

const bodyStyle = {
  width: '100%',
  height: '100vh',
};
/**
 * The main app that all components are rendered in.
 * All other components can be found as children within the components below.
 *  @author:kyhorne
 */
const App = () => (
  <div>
    <Header />
    <div style={{ ...bodyStyle }}>
      <Sidebar />
      <SmartGrid />
    </div>
  </div>
);

export default App;
