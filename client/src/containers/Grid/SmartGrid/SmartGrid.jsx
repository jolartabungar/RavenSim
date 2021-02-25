import React from 'react';
import { connect } from 'react-redux';
import { Layer, FastLayer } from 'react-konva';
import WirePreview from '../../Wire/WirePreview';
import WireFactory from '../../Wire/WireFactory';
import SimpleGrid from '../SimpleGrid';
import { gridBackgroundColor, cellSize } from '../../../util/style';
import StageWrapper from '../../../components/StageWrapper';
import ComponentFactory from '../../Component/ComponentFactory';
import {
  showComponentPreview,
  createComponent,
  showComponentShadow,
  hideComponentShadow,
} from '../../../store/component/actions';
import ComponentPreview from '../../Component/ComponentPreview';
import ComponentShadowFactory from '../../Component/ComponentShadowFactory';
import PortFactory from '../../PortFactory/PortFactory';
import Konva from 'konva';

const stageWrapperStyle = {
  width: 'auto',
  backgroundColor: gridBackgroundColor,
  height: 'inherit',
  overflow: 'hidden',
};
/**
 * Handles all grid activities, renders shadows of components, port placement,
 * dragging components, snapping them to fixed coordinates
 * @param {props} props
 * @author:kyhorne
 */
const App = (props) => {
  const roundToNearestCell = (i) => Math.round(i / cellSize) * cellSize;

  const translatePoint = (x, y) => [roundToNearestCell(x), roundToNearestCell(y)];

  const onMouseMove = (e) => {
    const { sidebar, shadow } = props.componentReducer;
    const { offsetX, offsetY } = e.evt;
    const point = translatePoint(offsetX, offsetY);
    // Do not show the component preview if there was nothing selected from
    // the sidebar.
    if (sidebar.type !== undefined) {
      props.showComponentPreview(offsetX, offsetY);
    }
    // Do not show the component shadow if the type is undefined.
    if (shadow.type !== undefined) {
      props.showComponentShadow(point[0], point[1]);
    }

    // Trigger event on drag move
    if (e.type === 'dragmove') {
      console.log('drag move');
    } else {
      // console.log(e);
    }
  };

  const onMouseUp = () => {
    const {
      sidebar: { x, y, type },
      shadow,
    } = props.componentReducer;
    // Only instantiate the component if something is currently being dragged
    // from the sidebar.
    if (type !== undefined) {
      const point = translatePoint(x, y);
      props.createComponent(type, point[0], point[1]);
    }
    // Only hide the shadow if it is currently visible.
    if (!shadow.isHidden) {
      props.hideComponentShadow();
    }
  };

  function haveIntersection(r1, r2) {
    console.log('r1 = ' + JSON.stringify(r1, null));
    console.log('r2 = ' + JSON.stringify(r2, null));
    console.log(`r1.x = ${r1.x}, r1.y = ${r1.y}, r1.width = ${r1.width}, r1.height = ${r1.height},`)
    console.log(`r2.x = ${r2.x}, r2.y = ${r2.y}, r2.width = ${r2.width}, r2.height = ${r2.height},`)
    return !(
      r2.x > r1.x + r1.width ||
      r2.x + r2.width < r1.x ||
      r2.y > r1.y + r1.height||
      r2.y + r2.height < r1.y
    );
  }

  let rectDrawn = false;

  const checkForCollisions = (e) => {
    console.log(e);
    console.log('in check for collision');
    const target = e.target;
    const targetRect = e.target.getClientRect();
    const layer = e.currentTarget;
    // console.log(layer);
    // For each child in layer object, check if there is a collision
    console.log('targetRect below' + targetRect);
    console.log(targetRect)
    layer.children.each(function (group) {
      // do not check intersection with self
      // console.log(group);
      if (group === target) {
        return;
      }
      if (haveIntersection(group.getClientRect(), targetRect)) {
        console.log('collision');
        if (!rectDrawn) {
          const selectionRect = new Konva.Rect({stroke: 'red', visible: true, listening: false});
          layer.add(selectionRect);
          selectionRect.setAttrs(group.getClientRect());
          layer.draw();
          rectDrawn = true;
        }
        
        // console.log('colliding with ' + group.name())
      } else {
        console.log('no collision');
      }
    });

  }

  return (
    <StageWrapper
      onMouseUp={() => onMouseUp()}
      onMouseMove={(e) => onMouseMove(e)}
      dragMove={(e) => onMouseMove(e)}
      dragEnd={() => onMouseUp()}
      style={stageWrapperStyle}
      onDragMove={(e) => checkForCollisions(e)}
    >
      <Layer>
        <SimpleGrid />
        <WirePreview />
      </Layer>
      <FastLayer>
        <WireFactory />
      </FastLayer>
      <Layer
        onDragMove={(e) => checkForCollisions(e)}
      >
        {/* <ComponentShadowFactory /> */}
        <ComponentPreview />
        <ComponentFactory />
        <PortFactory />
      </Layer>
    </StageWrapper>
  );
};

const mapStateToProps = (state) => ({
  componentReducer: state.componentReducer,
});

const mapDispatchToProps = {
  showComponentPreview,
  createComponent,
  showComponentShadow,
  hideComponentShadow,
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
