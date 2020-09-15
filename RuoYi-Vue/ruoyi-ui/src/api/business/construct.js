import request from '@/utils/request'

// 查询建筑篇章列表
export function listConstruct(query) {
  return request({
    url: '/business/construct/list',
    method: 'get',
    params: query
  })
}

// 查询建筑篇章详细
export function getConstruct(conId) {
  return request({
    url: '/business/construct/' + conId,
    method: 'get'
  })
}

// 新增建筑篇章
export function addConstruct(data) {
  return request({
    url: '/business/construct',
    method: 'post',
    data: data
  })
}

// 修改建筑篇章
export function updateConstruct(data) {
  return request({
    url: '/business/construct',
    method: 'put',
    data: data
  })
}

// 删除建筑篇章
export function delConstruct(conId) {
  return request({
    url: '/business/construct/' + conId,
    method: 'delete'
  })
}

// 导出建筑篇章
export function exportConstruct(query) {
  return request({
    url: '/business/construct/export',
    method: 'get',
    params: query
  })
}