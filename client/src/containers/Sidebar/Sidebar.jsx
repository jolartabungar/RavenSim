import React from 'react';
import { Layer } from 'react-konva';
import DropDownMenu from './DropDownMenu';
import AndGateShape from '../Component/ComponentShape/LogicGate/AndGateShape';
import StageWrapper from '../../components/StageWrapper';
import {
  sideBarFillColor,
  lineBreakColor,
  lineBreakWidth,
  smallLogicGateStrokeWidth,
  stageSize,
} from '../../util/style';
import ClockShape from '../Component/ComponentShape/Signal/ClockShape';
import NotGateShape from '../Component/ComponentShape/LogicGate/NotGateShape';
import XorGateShape from '../Component/ComponentShape/LogicGate/XorGateShape';
import OrGateShape from '../Component/ComponentShape/LogicGate/OrGateShape';
import NandGateShape from '../Component/ComponentShape/LogicGate/NandGateShape';
import NorGateShape from '../Component/ComponentShape/LogicGate/NorGateShape';
import XnorGateShape from '../Component/ComponentShape/LogicGate/XnorGateShape';
import DFlipFlopShape from '../Component/ComponentShape/Memory/DFlipFlopShape';
import TFlipFlopShape from '../Component/ComponentShape/Memory/TFlipFlopShape';
import JKFlipFlopShape from '../Component/ComponentShape/Memory/JKFlipFlopShape';
import RSFlipFlopShape from '../Component/ComponentShape/Memory/RSFlipFlopShape';
import SourceShape from '../Component/ComponentShape/Wiring/SourceShape';
import HalfAdderShape from '../Component/ComponentShape/Arithmetic/HalfAdderShape';
import FullAdderShape from '../Component/ComponentShape/Arithmetic/FullAdderShape';
import HalfSubtractorShape from '../Component/ComponentShape/Arithmetic/HalfSubtractorShape';
import FullSubtractorShape from '../Component/ComponentShape/Arithmetic/FullSubtractorShape';
import EighttoThreeEncoderShape from '../Component/ComponentShape/Plexers/EighttoThreeEncoderShape';
import ThreetoEightDecoderShape from '../Component/ComponentShape/Plexers/ThreetoEightDecoderShape';
import TwoToOneMuxShape from '../Component/ComponentShape/Plexers/TwoToOneMuxShape';
import InputButtonShape from '../Component/ComponentShape/Signal/InputButtonShape';

const sidebarStyle = {
  height: 'inherit',
  width: '15%',
  backgroundColor: sideBarFillColor,
  float: 'left',
  overflow: 'scroll',
  minWidth: '200px',
};

const lineBreakStyle = {
  margin: 0,
  borderWidth: lineBreakWidth,
  borderColor: lineBreakColor,
  width: '100%',
};

const stageStyle = {
  margin: '0 auto',
  height: stageSize + smallLogicGateStrokeWidth * 2,
  width: stageSize + smallLogicGateStrokeWidth * 2,
};

const menuItems = [
  { name: 'WIRING', children: [] },
  {
    name: 'GATES',
    children: [
      <AndGateShape isSmall />,
      <NandGateShape isSmall />,
      <NotGateShape isSmall />,
      <OrGateShape isSmall />,
      <NorGateShape isSmall />,
      <XorGateShape isSmall />,
      <XnorGateShape isSmall />,
    ],
  },
  { name: 'PLEXERS', 
    children: [
      <EighttoThreeEncoderShape isSmall />,
      <ThreetoEightDecoderShape isSmall />,
      <TwoToOneMuxShape isSmall />,
    ],
  },
  { name: 'ARITHMETIC', 
    children: [
      <HalfAdderShape isSmall />,
      <FullAdderShape isSmall />,
      <HalfSubtractorShape isSmall />,
      <FullSubtractorShape isSmall />,
    ],
  },
  {
    name: 'MEMORY',
    children: [
      <DFlipFlopShape isSmall />, 
      <RSFlipFlopShape isSmall />,
      <JKFlipFlopShape isSmall />,
    ],
  },
  {
    name: 'INPUT/OUTPUT',
    children: [<ClockShape isSmall />, <InputButtonShape isSmall />],
  },
  { name: 'BASE', children: [] },
];

/**
 * Maps all sidebar items to a corresponding dropdown menu, and enables them to
 * be hidden, or dragged onto the adjacent grid.
 * @author: kyhorne
 */
const Sidebar = () => (
  <div style={{ ...sidebarStyle }}>
    {menuItems.map((item) => (
      <div>
        <DropDownMenu title={item.name} types={item.types}>
          {React.Children.map(item.children, (child) => (
            <StageWrapper type={child.type} style={{ ...stageStyle }}>
              <Layer>{child}</Layer>
            </StageWrapper>
          ))}
        </DropDownMenu>
        <hr style={{ ...lineBreakStyle }} />
      </div>
    ))}
  </div>
);

export default Sidebar;
