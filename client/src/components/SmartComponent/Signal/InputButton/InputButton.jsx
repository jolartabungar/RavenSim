import React from 'react';
import InputButtonShape from '../../../../containers/Component/ComponentShape/Signal/InputButtonShape';
import DraggableComponent from '../../../DraggableComponent';
import { INPUT_BUTTON } from '../../../../store/component/types';

const InputButton = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={INPUT_BUTTON}>
    <InputButtonShape />
  </DraggableComponent>
);

export default InputButton;
