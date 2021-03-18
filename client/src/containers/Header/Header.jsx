import React, { useState, useEffect} from 'react';
import { connect } from 'react-redux';
import Button from '../Button';
import { startSimulation, togglePoke, loadCircuit, saveCircuit } from '../../store/command/actions';
import { headerBackgroundColor, headerHeight } from '../../util/style'

/**
 * The header bar in the web app, load this with buttons that the user can use to
 * save/load/simulate, etc
 * @author:kyhorne
 */
const headerStyle = {
  width: 'inherit',
  backgroundColor: headerBackgroundColor,
  height: `${headerHeight}vh`,
  display: 'flex',
  alignItems: 'center',
};


const Header = (props) => {
  const localStorageKey = 'myValueInLocalStorage';
  const circuitNamesKey = 'circuitNames';

  const [fileName, setFileName] = useState(
    localStorage.getItem(localStorageKey) || ''
  );
  
  const [isSaveClicked, setIsSaveClicked] = useState(false);

  const [state, setState] = useState(
    localStorage.getItem(localStorageKey) || ''
  );

  const [isLoadClicked, setIsLoadClicked] = useState(false);

  const onChange = event => {
    isSaveClicked && setIsSaveClicked(false);
    setFileName(event.target.value)
  };

  const onChangeLoad = event => {
    isLoadClicked && setIsLoadClicked(false);
    setState(event.target.value)
  }

  const onSaveClicked = () => {
    setIsSaveClicked(true);
    return props.saveCircuit(fileName)
  };
  
  const onLoadClicked = () => {
    setIsLoadClicked(true);
    return props.loadCircuit(state)
  };

  const createOptionsList = () => {
    const circuitNamesList = JSON.parse(localStorage.getItem(circuitNamesKey));
    if(circuitNamesList == null){
      return;
    }
    const createdOptionsList = circuitNamesList.map((circuitName) => 
      <option>{circuitName}</option>);
    return createdOptionsList
  }

  useEffect(() => {
    if (!fileName || !fileName.trim() || !isSaveClicked){
      return;
    }

    const circuitNames = JSON.parse(localStorage.getItem(circuitNamesKey)) || [];
    const updatedcircuitNames = circuitNames
      .filter((currFileName) => currFileName !== fileName);
    updatedcircuitNames.push(fileName);
    
    localStorage.setItem(circuitNamesKey, JSON.stringify(updatedcircuitNames));
    localStorage.setItem(localStorageKey, fileName);
  }, [isSaveClicked]);

  return (
    <div style={{ ...headerStyle }}>
      <input value={fileName} type="text" onChange={onChange} />
      <Button text="TOGGLE POKE" onClick={() => props.togglePoke()} />
      <Button text="SIMULATE" onClick={() => props.startSimulation()} />
      <Button
        text="LOAD CIRCUIT" 
        onClick={onLoadClicked}
      />
      <Button
        text="SAVE CIRCUIT"
        onClick={onSaveClicked} 
      />
      <select value={state} name= "Saved Files" onChange = {onChangeLoad}>
        {createOptionsList()}
      </select>
    </div>
  );
};

const mapDispatchToProps = {
  startSimulation,
  togglePoke,
  loadCircuit,
  saveCircuit
};

export default connect(null, mapDispatchToProps)(Header);
