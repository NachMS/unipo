var ThreeJSTest = (function () {
    function ThreeJSTest() {
        this.screenWidth = window.innerWidth;
        this.screenHeight = window.innerHeight;
        this.flameCnt = 0;
        this.createRenderer();
        this.createScene();
    }
    ThreeJSTest.prototype.createRenderer = function () {
        this.renderer = new THREE.WebGLRenderer();
        this.renderer.setSize(this.screenWidth, this.screenHeight);
        this.renderer.setClearColor(new THREE.Color(0x0d47a1));
        this.renderer.shadowMap.enabled = true;
        document.body.appendChild(this.renderer.domElement);
    };
    ThreeJSTest.prototype.createScene = function () {
        //シーンの作成
        this.scene = new THREE.Scene();
        //カメラの作成
        this.camera = new THREE.PerspectiveCamera(45, this.screenWidth / this.screenHeight, 0.1, 1000);
        this.camera.position.x = -20;
        this.camera.position.y = 30;
        this.camera.position.z = 40;
        this.camera.lookAt(new THREE.Vector3(0, 0, 0));
        //ライトの作成
        this.light = new THREE.SpotLight(0xffffff);
        this.light.position.set(-20, 30, -5);
        this.light.castShadow = true;
        // 平面の作成
        this.planeGeometry = new THREE.PlaneGeometry(60, 40, 1, 1);
        this.planeMaterial = new THREE.MeshLambertMaterial({ color: 0xffffff });
        this.plane = new THREE.Mesh(this.planeGeometry, this.planeMaterial);
        this.plane.rotation.x = -Math.PI / 2;
        this.plane.position.y = 0;
        this.plane.receiveShadow = true; //影の追加
        //シーンへの追加
        this.scene.add(this.camera);
        this.scene.add(this.light);
        this.addSphere();
    };
    ThreeJSTest.prototype.render = function () {
        this.addObject.rotation.y += 0.02;
        this.renderer.render(this.scene, this.camera);
        requestAnimationFrame(this.render.bind(this));
        this.flameCnt++;
    };

    ThreeJSTest.prototype.addSphere = function () {
        //Geometryの生成
        var SphereSize = Math.ceil(20);
        var addObjectGeometry = new THREE.SphereGeometry(SphereSize); //追加分
        //Materialの生成
        var meshMaterial = new THREE.MeshNormalMaterial({ side: THREE.DoubleSide });
        var wireMaterial = new THREE.MeshBasicMaterial({ wireframe: true });
        //オブジェクトの生成
        this.addObject = THREE.SceneUtils.createMultiMaterialObject(addObjectGeometry, [meshMaterial, wireMaterial]);
        this.scene.add(this.addObject);
    };
    return ThreeJSTest;
}());
window.onload = function () {
    var threeJSTest = new ThreeJSTest();
    threeJSTest.render();
};
