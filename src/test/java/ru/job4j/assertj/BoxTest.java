package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 12);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(20, 30);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }


    @Test
    void whenNumberOfVerticesIs4() {
        Box box = new Box(4, 6);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(4);
    }

    @Test
    void whenNumberOfVerticesIs6() {
        Box box = new Box(6, 12);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isEqualTo(6);
    }

    @Test
    void whenObjIsExist() {
        Box box = new Box(4, 6);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void whenObjIsNotExist() {
        Box box = new Box(1, 1);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void whenObjHasArea() {
        Box box = new Box(6, 12);
        double area = box.getArea();
        assertThat(area).isEqualTo(864)
                .isNotEqualTo(0.0);
    }

    @Test
    void whenObjHasNotArea() {
        Box box = new Box(1, 1);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0);
    }
}