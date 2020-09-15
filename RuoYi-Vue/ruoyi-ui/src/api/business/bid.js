import request from '@/utils/request'

// 查询招标文章列表
export function listBid(query) {
  return request({
    url: '/business/bid/list',
    method: 'get',
    params: query
  })
}

// 查询招标文章详细
export function getBid(bidId) {
  return request({
    url: '/business/bid/' + bidId,
    method: 'get'
  })
}

// 新增招标文章
export function addBid(data) {
  return request({
    url: '/business/bid',
    method: 'post',
    data: data
  })
}

// 修改招标文章
export function updateBid(data) {
  return request({
    url: '/business/bid',
    method: 'put',
    data: data
  })
}

// 删除招标文章
export function delBid(bidId) {
  return request({
    url: '/business/bid/' + bidId,
    method: 'delete'
  })
}

// 导出招标文章
export function exportBid(query) {
  return request({
    url: '/business/bid/export',
    method: 'get',
    params: query
  })
}