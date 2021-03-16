import React from 'react';
import Collapsible from 'react-collapsible';
import { connect } from 'react-redux';
import MenuItemTitle from '../MenuItemTitle';
import { setComponentPreviewType, setComponentShadowType } from '../../../store/component/actions';
import { componentWidth } from '../../../util/style';
import {
  AND_GATE,
  THREE_INPUT_AND_GATE,
  THREE_INPUT_NOR_GATE,
  THREE_INPUT_OR_GATE,
  THREE_INPUT_NAND_GATE,
  CLOCK,
  NOT_GATE,
  XOR_GATE,
  OR_GATE,
  NAND_GATE,
  NOR_GATE,
  XNOR_GATE,
  D_FLIP_FLOP,
  T_FLIP_FLOP,
  JK_FLIP_FLOP,
  RS_FLIP_FLOP,
  SOURCE,
  INPUT_BUTTON,
} from '../../../store/component/types';
import AndGateShape from '../../Component/ComponentShape/LogicGate/AndGateShape';
import ThreeInputAndGateShape from '../../Component/ComponentShape/LogicGate/ThreeInputAndGateShape';
import ThreeInputNorGateShape from '../../Component/ComponentShape/LogicGate/ThreeInputNorGateShape';
import ThreeInputOrGateShape from '../../Component/ComponentShape/LogicGate/ThreeInputOrGateShape';
import ThreeInputNandGateShape from '../../Component/ComponentShape/LogicGate/ThreeInputNandGateShape';
import ClockShape from '../../Component/ComponentShape/Signal/ClockShape';
import NotGateShape from '../../Component/ComponentShape/LogicGate/NotGateShape';
import XorGateShape from '../../Component/ComponentShape/LogicGate/XorGateShape';
import OrGateShape from '../../Component/ComponentShape/LogicGate/OrGateShape';
import NandGateShape from '../../Component/ComponentShape/LogicGate/NandGateShape';
import NorGateShape from '../../Component/ComponentShape/LogicGate/NorGateShape';
import XnorGateShape from '../../Component/ComponentShape/LogicGate/XnorGateShape';
import DFlipFlopShape from '../../Component/ComponentShape/Memory/DFlipFlopShape';
import TFlipFlopShape from '../../Component/ComponentShape/Memory/TFlipFlopShape';
import JKFlipFlopShape from '../../Component/ComponentShape/Memory/JKFlipFlopShape';
import RSFlipFlopShape from '../../Component/ComponentShape/Memory/RSFlipFlopShape';
import SourceShape from '../../Component/ComponentShape/Wiring/SourceShape';
import InputButtonShape from '../../Component/ComponentShape/Signal/InputButtonShape';
import AttributeTable from '../../Component/ComponentShape/AttributeTable';

/**
 * Given a sidebar item, creats a new Component when a user begins clicking on an element.
 * @param {*} props a list of items in the sidebar.
 * @author:kyhorne
 */
const DropDownMenu = (props) => {
  const { title, children } = props;

  const onMouseDown = (instanceOf) => {
    let type;
    switch (instanceOf) {
      case AndGateShape:
        type = AND_GATE;
        break;
      case ThreeInputAndGateShape:
        type = THREE_INPUT_AND_GATE;
        break;
      case ClockShape:
        type = CLOCK;
        break;
      case SourceShape:
        type = SOURCE;
        break;
      case NotGateShape:
        type = NOT_GATE;
        break;
      case XorGateShape:
        type = XOR_GATE;
        break;
      case OrGateShape:
        type = OR_GATE;
        break;
      case ThreeInputOrGateShape:
        type = THREE_INPUT_OR_GATE;
        break;
      case NandGateShape:
        type = NAND_GATE;
        break;
        case ThreeInputNandGateShape:
          type = THREE_INPUT_NAND_GATE;
          break;
      case NorGateShape:
        type = NOR_GATE;
        break;
      case ThreeInputNorGateShape:
        type = THREE_INPUT_NOR_GATE;
      break;
      case XnorGateShape:
        type = XNOR_GATE;
        break;
      case DFlipFlopShape:
        type = D_FLIP_FLOP;
        break;
      case TFlipFlopShape:
        type = T_FLIP_FLOP;
        break;
      case JKFlipFlopShape:
        type = JK_FLIP_FLOP;
        break;
      case RSFlipFlopShape:
        type = RS_FLIP_FLOP;
        break;
      case InputButtonShape:
        type = INPUT_BUTTON;
        break;
      // AttributeTable should NOT create new component but we also need to handle it
      // just a case that simply returns. 
      // TODO: Ask Fejiro if this is the best thing.
      case AttributeTable:
        return;
      default:
        throw new Error(`${type}: is an unimplemented type in the DropDownMenu`);
    }
    props.setComponentShadowType(type);
    props.setComponentPreviewType(type);
  };

  return (
    <div>
      <Collapsible
        triggerOpenedClassName="down"
        triggerClassName="right"
        open
        trigger={<MenuItemTitle title={title} />}
      >
        <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          {React.Children.map(children, (child) => (
            // eslint-disable-next-line jsx-a11y/no-static-element-interactions
            // set width to 50% for AttributeTable, then set width to componentWidth for every other component
            <div
              style={
                { 
                  marginBottom: '20px',
                  width: `${child.props.type !== AttributeTable ? componentWidth : 50}%` 
                }
              }
              onMouseDown={() => onMouseDown(child.props.type)}
              onTouchStart={() => onMouseDown(child.props.type)}
            >
              {child}
            </div>
          ))}
        </div>
      </Collapsible>
    </div>
  );
};

const mapDispatchToProps = {
  setComponentPreviewType,
  setComponentShadowType,
};

export default connect(null, mapDispatchToProps)(DropDownMenu);
