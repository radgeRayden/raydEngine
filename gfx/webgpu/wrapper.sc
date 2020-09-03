import .raydEngine.use
using import radlib.core-extensions
let wgpu = (import foreign.wgpu-native)

inline typeinfo (sym dropf)
    do
        let
            typesym = sym
            dropf = dropf
        locals;

let handle-types =
    va-lfold (Scope)
        inline (__ info scope)
            let T = (getattr wgpu info.typesym)
            let handleT = (make-handle-type info.typesym (storageof T) info.dropf)
            'bind scope info.typesym handleT
        typeinfo 'AdapterId wgpu.adapter_destroy
        typeinfo 'BindGroupId wgpu.bind_group_destroy
        typeinfo 'BindGroupLayoutId wgpu.bind_group_layout_destroy
        typeinfo 'BufferId wgpu.buffer_destroy
        typeinfo 'CommandBufferId wgpu.command_buffer_destroy
        typeinfo 'CommandEncoderId wgpu.command_encoder_destroy
        typeinfo 'DeviceId wgpu.device_destroy
        typeinfo 'PipelineLayoutId wgpu.pipeline_layout_destroy
        typeinfo 'RenderPipelineId wgpu.render_pipeline_destroy
        typeinfo 'ComputePipelineId wgpu.compute_pipeline_destroy
        typeinfo 'RenderPassId wgpu.render_pass_destroy
        typeinfo 'ComputePassId wgpu.compute_pass_destroy
        typeinfo 'SamplerId wgpu.sampler_destroy
        typeinfo 'ShaderModuleId wgpu.shader_module_destroy
        typeinfo 'TextureId wgpu.shader_module_destroy wgpu.texture_destroy
        typeinfo 'TextureViewId wgpu.shader_module_destroy wgpu.texture_view_destroy

run-stage;

inline constructor-info (return-type fn-sym)
    do
        let
            returnT = return-type
            sym = fn-sym
        locals;

let constructors =
    va-lfold (Scope)
        inline (__ info scope)
            let createf = (getattr wgpu info.sym)
            let newf =
                inline (...)
                    let handle = (createf ...)
                    info.returnT handle
            'bind scope info.sym newf
        constructor-info handle-types.BindGroupId 'device_create_bind_group
        constructor-info handle-types.BindGroupLayoutId 'device_create_bind_group_layout
        constructor-info handle-types.BufferId 'device_create_buffer
        constructor-info handle-types.CommandEncoderId 'device_create_command_encoder
        constructor-info handle-types.ComputePipelineId 'device_create_compute_pipeline
        constructor-info handle-types.PipelineLayoutId 'device_create_pipeline_layout
        constructor-info handle-types.RenderPipelineId 'device_create_render_pipeline
        constructor-info handle-types.SamplerId 'device_create_sampler
        constructor-info handle-types.ShaderModuleId 'device_create_shader_module
        constructor-info handle-types.TextureId 'device_create_texture
        constructor-info handle-types.TextureViewId 'texture_create_view
        constructor-info handle-types.RenderPassId 'command_encoder_begin_render_pass
        constructor-info handle-types.ComputePassId 'command_encoder_begin_compute_pass

run-stage;

do
    using wgpu
    using handle-types

    inline... render_pass_end_pass (id : RenderPassId,)
        wgpu.render_pass_end_pass id
        lose id

    inline... compute_pass_end_pass (id : ComputePassId,)
        wgpu.compute_pass_end_pass id
        lose id

    inline queue_submit (queue command-buffer)
        local cmdbuf : wgpu.CommandBufferId = command-buffer
        wgpu.queue_submit queue &cmdbuf 1
        lose command-buffer

    inline command_encoder_finish (id desc)
        let handle = (wgpu.command_encoder_finish id desc)
        lose id
        handle as CommandBufferId

    locals;
