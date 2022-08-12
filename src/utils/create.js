import Vue from 'vue'

/**
 * 在class .builderBox中创建组件
 * @param Component 组件实例的选项对象
 * @param props 组件实例中的prop
 */
export function create(Component, props) {
    const comp = new(Vue.extend(Component))({
        propsData: props
    }).$mount()
    let htmlObj = document.getElementById("builderBox")
    htmlObj.appendChild(comp.$el)
    
    //  document.body.appendChild(comp.$el)
    comp.remove = () => {
        // document.body.removeChild(comp.$el)
        let htmlObj = document.getElementById("builderBox")
        htmlObj.removeChild(comp.$el)
        comp.$destroy()
    }

    return comp
}